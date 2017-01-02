package com.windstream.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.windstream.model.SampleData;

public interface SampleRepo extends JpaRepository<SampleData, Long> {

	
}
