package cn.deepcoding.service;

import java.util.Map;

import cn.deepcoding.model.Referrer;

public interface ReferrerService {

	Map<String, Object> getAll(Referrer referrer, Integer page, Integer rows);

}
