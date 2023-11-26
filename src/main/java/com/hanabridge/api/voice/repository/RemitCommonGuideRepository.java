package com.hanabridge.api.voice.repository;

import com.hanabridge.api.voice.domain.RemitCommonGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitCommonGuideRepository extends JpaRepository<RemitCommonGuide, Long> {

}
