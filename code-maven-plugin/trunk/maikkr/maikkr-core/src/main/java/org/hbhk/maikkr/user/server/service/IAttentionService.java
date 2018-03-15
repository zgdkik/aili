package org.hbhk.maikkr.user.server.service;

import org.hbhk.maikkr.user.share.pojo.AttentionInfo;

/**
 * Dao接口开发规范 1.类名必须以I开头、以Dao结尾
 */

public interface IAttentionService {
	AttentionInfo save(AttentionInfo attention);
}