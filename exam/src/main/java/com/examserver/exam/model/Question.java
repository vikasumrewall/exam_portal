package com.examserver.exam.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	
	
	private String content;

    private String image;

    private String option1;
    private String option2;
    private String option3;
    private String option4;


    private String answer;

    @Transient
    private  String givenAnswer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	
}
