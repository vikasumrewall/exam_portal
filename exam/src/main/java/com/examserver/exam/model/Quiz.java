package com.examserver.exam.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;

    @Column(length = 5000)
    private String description;

    private String maxMarks;

    private String numberOfQuestions;

    private Boolean active ;
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JsonIgnore
	private  Set<Question> question;
	
	
	
	

}
