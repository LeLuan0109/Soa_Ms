package com.project.app.org.repository;


import com.project.app.org.domain.TblOrg;
import com.project.app.org.dto.OrgDto;
import com.project.app.org.dto.OrgRelationshipDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblOrgRepository  extends JpaRepository<TblOrg, Integer> {

	@Query("SELECT org.id FROM TblOrg org")
	List<Integer> getAllOrgIds();

	@Query(
			"SELECT new com.project.app.org.dto.OrgDto("
					+ "og.id, "
					+ "og.address, "
					+ "og.companyName, "
					+ "og.email, "
					+ "og.phone,"
					+ "og.logo) "
					+ "FROM TblOrg  as og "
					+ "WHERE 1= 1 AND (:companyName is null OR og.companyName = :companyName) "
	)
	Page<OrgDto> search(
			@Param("companyName") String companyName,
			Pageable pageable
	);
	@Query("select new TblOrg("
			+ "p.id,"
			+ "p.address,"
			+ "p.companyName,"
			+ "p.email,"
			+ "p.phone,"
			+ "p.logo,"
			+ "p.taxCode,"
			+ "p.website) "
			+ "from TblOrg as p where p.id in (:id) "
			+ "ORDER BY p.id ASC ")
	List<TblOrg> filterOrganization(
			@Param("id") List<Integer> id
	);

	@Query("SELECT p.id FROM TblOrg p")
	List<Integer> getAllOrgId();


	@Query("SELECT  new com.project.app.org.dto.OrgDto("
			+  "p.id, "
			+ "p.address, "
			+ "p.companyName, "
			+ "p.email, "
			+ "p.phone,"
			+ "p.logo) "
			+ " FROM TblOrg p "
	)
	List<OrgDto> getOrgDtoAll();

	@Query("SELECT  new com.project.app.org.dto.OrgRelationshipDto("
			+ "p.id, "
			+ "p.companyName "
			+ ") FROM TblOrg p "
			+ "WHERE p.id = :id ")
	OrgRelationshipDto getOrgById(
			@Param("id") Integer id
	);

	Optional<TblOrg> findByName(String name);

	List<TblOrg> findByIdIn(List<Integer> ids);

	@Query("SELECT new com.project.app.org.dto.OrgDto(" +
			"o.id, " +
			"o.address, " +
			"o.companyName, " +
			"o.email, " +
			"o.phone, " +
			"o.logo) " +
			"FROM TblOrg o " +
			"WHERE o.id = :id")
	Optional<OrgDto> findOrgDtoById(@Param("id") Integer id);

	@Query("SELECT new com.project.app.org.dto.OrgDto(" +
			"o.id, " +
			"o.address, " +
			"o.companyName, " +
			"o.email, " +
			"o.phone, " +
			"o.logo) " +
			"FROM TblOrg o " +
			"WHERE o.companyName = :companyName")
	Optional<OrgDto> findOrgDtoByCompanyName(@Param("companyName") String companyName);
}
