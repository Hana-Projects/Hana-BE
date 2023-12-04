package com.hanabridge.api.voice.repository;

import com.hanabridge.api.voice.domain.ItemGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGuideRepository extends JpaRepository<ItemGuide, Long> {

}
