package br.com.alura.forum.controller.dto.input;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

public class TopicSearchInputDto {
	private TopicStatus status;
	private String categoryName;
	
	public TopicStatus getStatus() {
		return status;
	}
	public void setStatus(TopicStatus status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Specification<Topic> buildSpecification() {
		
		Specification<Topic> specification = (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if (status != null) {
				Predicate predicateStatus = criteriaBuilder.equal(root.get("status"), status);
				predicates.add(predicateStatus);
			}
			
			if (categoryName != null) {
				Predicate predicateCategoryName = 
						criteriaBuilder.equal(
								root.get("course").get("subcategory").get("category").get("name"), 
								categoryName);
				predicates.add(predicateCategoryName);
			}
			
			Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
			
			return predicate;
		};
		return specification;
	}
}
