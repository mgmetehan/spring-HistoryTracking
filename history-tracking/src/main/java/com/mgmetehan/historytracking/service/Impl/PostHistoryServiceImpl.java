package com.mgmetehan.historytracking.service.Impl;

import com.mgmetehan.historytracking.core.repository.BaseJpaRepository;
import com.mgmetehan.historytracking.core.service.AbstractEntityService;
import com.mgmetehan.historytracking.model.PostHistory;
import com.mgmetehan.historytracking.repository.PostHistoryRepository;
import com.mgmetehan.historytracking.service.PostHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostHistoryServiceImpl extends AbstractEntityService<PostHistory,Long> implements PostHistoryService {
    private final PostHistoryRepository postHistoryRepository;

    @Override
    public BaseJpaRepository<PostHistory, Long> getJpaRepository() {
        return null;
    }
}
