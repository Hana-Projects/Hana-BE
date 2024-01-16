package com.hanabridge.api.voice.repository;

import com.hanabridge.api.voice.domain.RemitPhoneGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitPhoneGuideRepository extends JpaRepository<RemitPhoneGuide, Long> {

}
