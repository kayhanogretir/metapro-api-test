package com.davon.metapro.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davon.metapro.api.domain.Message;

@Repository
public interface MessageDao extends JpaRepository<Message, Long>{

}
