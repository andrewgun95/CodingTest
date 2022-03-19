package com.geekseat.witchsaga.service.common;


import com.geekseat.witchsaga.service.common.exceptions.DataNotFoundException;

import java.util.List;

/**
 * CRUD Service
 * @param <T> DTO
 */
public interface CrudService<T> {

    T createOrUpdate(T dto);

    T findByOne(Long id) throws DataNotFoundException;

    List<T> findByAll();

    void delete(Long id);

}

