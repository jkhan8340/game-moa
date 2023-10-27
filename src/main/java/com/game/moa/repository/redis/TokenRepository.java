package com.game.moa.repository.redis;

import com.game.moa.vo.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<RefreshToken, Long> {

    RefreshToken findByAccessToken(String accessToken);

}
