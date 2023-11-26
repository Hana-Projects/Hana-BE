package com.hanabridge.api.voice.repository;

import com.hanabridge.api.voice.domain.RemitAccountGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitAccountGuideRepository extends JpaRepository<RemitAccountGuide,Long> {

}
