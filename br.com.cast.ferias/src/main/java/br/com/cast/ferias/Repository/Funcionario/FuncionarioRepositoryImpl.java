package br.com.cast.ferias.Repository.Funcionario;

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

import br.com.cast.ferias.Model.Funcionario;


public class FuncionarioRepositoryImpl implements FuncionarioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<?> filtrar(FuncionarioFiltro funcionarioFiltro, Pageable paginacao) {
		CriteriaBuilder builder 			= manager.getCriteriaBuilder();
		CriteriaQuery<Funcionario> criteria  	= builder.createQuery(Funcionario.class);
		Root<Funcionario> root 				= criteria.from(Funcionario.class);
		Predicate[] predicates 				= criarRestricoes(funcionarioFiltro, builder, root);
		criteria.where(predicates);
		TypedQuery<Funcionario> qry 			= manager.createQuery(criteria);
		
		adicionaRestricoesPaginacao(qry, paginacao);
		return new PageImpl<>(qry.getResultList(), paginacao, total(funcionarioFiltro));
	}
	
	private Predicate[] criarRestricoes(FuncionarioFiltro funcionarioFiltro, CriteriaBuilder builder, Root<Funcionario> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(funcionarioFiltro.getCodigo())) {
			
			if(funcionarioFiltro.getCodigo() > 0) {				
				predicates.add(builder.equal(root.get("codigo"), funcionarioFiltro.getCodigo()));
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
	
	private Long total(FuncionarioFiltro funcionarioFiltro) {
		
		CriteriaBuilder builder 	 = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Funcionario> root 		 = criteria.from(Funcionario.class);
		List<Predicate> predicates 	= new ArrayList<Predicate>();
		criteria.select(builder.count(root)).where(predicates.toArray(new Predicate[] {}));
		return manager.createQuery(criteria).getSingleResult();
	}	
}
