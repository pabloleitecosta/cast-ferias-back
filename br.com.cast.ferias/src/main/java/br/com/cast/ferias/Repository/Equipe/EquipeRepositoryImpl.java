package br.com.cast.ferias.Repository.Equipe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.cast.ferias.Model.Equipe;


public class EquipeRepositoryImpl implements EquipeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;	
	
	@Override
	public Page<?> filtrar(EquipeFiltro equipeFiltro, Pageable paginacao) {

		CriteriaBuilder builder 		= manager.getCriteriaBuilder();
		CriteriaQuery<Equipe> criteria  = builder.createQuery(Equipe.class);
		Root<Equipe> root 				= criteria.from(Equipe.class);
		Predicate[] predicates 			= criarRestricoes(equipeFiltro, builder, root);
		criteria.where(predicates);
		TypedQuery<Equipe> qry 			= manager.createQuery(criteria);
		
		adicionaRestricoesPaginacao(qry, paginacao);
		return new PageImpl<>(qry.getResultList(), paginacao, total(equipeFiltro));
	}

	private Predicate[] criarRestricoes(EquipeFiltro categoriaFiltro, CriteriaBuilder builder, Root<Equipe> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(categoriaFiltro.getId())) {
			
			if(categoriaFiltro.getId() > 0) {				
				predicates.add(builder.equal(root.get("codigo"), categoriaFiltro.getId()));
			}
		}	
		return predicates.toArray(new Predicate[predicates.size()]);
	}	

	private void adicionaRestricoesPaginacao(TypedQuery<?> qry, Pageable paginacao) {
		
		int paginaAtual 			= paginacao.getPageNumber();
		int totalRegistrosPorPagina = paginacao.getPageSize();
		int primeiroRegistroPagina 	= paginaAtual * totalRegistrosPorPagina;		
		qry.setFirstResult(primeiroRegistroPagina);
		qry.setMaxResults(totalRegistrosPorPagina);		
	}
	
	private Long total(EquipeFiltro equipeFiltro) {
		
		CriteriaBuilder builder 	 = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Equipe> root 		 = criteria.from(Equipe.class);
		List<Predicate> predicates 	= new ArrayList<Predicate>();
		criteria.select(builder.count(root)).where(predicates.toArray(new Predicate[] {}));
		return manager.createQuery(criteria).getSingleResult();
	}	

}
