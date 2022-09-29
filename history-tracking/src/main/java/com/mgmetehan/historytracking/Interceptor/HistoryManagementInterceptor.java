package com.mgmetehan.historytracking.Interceptor;

import com.mgmetehan.historytracking.core.model.BaseHistoryEntity;
import com.mgmetehan.historytracking.core.model.ChangeEntry;
import com.mgmetehan.historytracking.core.service.AbstractEntityService;
import com.mgmetehan.historytracking.shared.annotation.Historical;
import com.mgmetehan.historytracking.shared.util.ContextWrapper;
import com.mgmetehan.historytracking.shared.util.JSONObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Slf4j
public class HistoryManagementInterceptor extends EmptyInterceptor {
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        saveHistory(entity, (Long) id, currentState, previousState, propertyNames);
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        saveHistory(entity, (Long) id, state, null, propertyNames);
        return super.onSave(entity, id, state, propertyNames, types);
    }

    private void saveHistory(Object entity, Long id, Object[] currentState, Object[] previousState, String[] propertyNames) {
        try {
            if (isHistoricalEntity(entity)) {
                Pair<String, BaseHistoryEntity> historyServiceNameAndHistoryInstance = getHistoryServiceNameAndHistoryInstance(entity);
                String historyServiceBeanName = historyServiceNameAndHistoryInstance.getFirst();

                var historyService = ContextWrapper.getContext().getBean(historyServiceBeanName, AbstractEntityService.class);

                BaseHistoryEntity historyEntityInstance = historyServiceNameAndHistoryInstance.getSecond();

                historyEntityInstance.setMainEntityId(id);

                ChangeEntry changeEntry = new ChangeEntry();

                var newObject = prepareObject(currentState, propertyNames);
                changeEntry.setNewValues(JSONObjectUtil.toString(newObject));

                if (Objects.nonNull(previousState)) {
                    var oldObject = prepareObject(previousState, propertyNames);
                    changeEntry.setOldValues(JSONObjectUtil.toString(oldObject));
                }

                List<String> changedCells = getChangedCells(currentState, previousState, propertyNames);

                changeEntry.setChangedCells(String.join(",", changedCells));

                historyEntityInstance.setChangeEntry(changeEntry);

                historyService.save(historyEntityInstance);
                log.info("[saveHistory] History saved successfully. History: {}", historyEntityInstance.toString());
            }
        } catch (Exception ex) {
            log.error("History didn't saved : {}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private List<String> getChangedCells(Object[] currentState, Object[] previousState, String[] propertyNames) {
        List<String> changedCells = new ArrayList<>();

        if (Objects.isNull(previousState)) {
            changedCells = Arrays.asList(propertyNames);
        } else {
            for (var i = 0; i < propertyNames.length; i++) {
                if (!Objects.equals(currentState[i], previousState[i])) {
                    changedCells.add(propertyNames[i]);
                }
            }
        }

        return changedCells;
    }

    private HashMap prepareObject(Object[] state, String[] propertyNames) {
        var baseHashMap = new HashMap<String, Object>();

        for (var i = 0; i < propertyNames.length; i++) {
            var key = propertyNames[i];
            var value = state[i] != null ? state[i] : null;
            baseHashMap.put(key, value);
        }
        return baseHashMap;
    }

    private boolean isHistoricalEntity(Object entity) {
        Annotation[] annotations = entity.getClass().getAnnotations();

        return Arrays.stream(annotations).anyMatch(annotation -> annotation.annotationType().equals(Historical.class));
    }

    private Pair<String, BaseHistoryEntity> getHistoryServiceNameAndHistoryInstance(Object entity) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Historical historicalAnnotation = entity.getClass().getAnnotation(Historical.class);

        return Pair.of(historicalAnnotation.serviceName(), (BaseHistoryEntity) historicalAnnotation.clazz().getDeclaredConstructor().newInstance());
    }
}
