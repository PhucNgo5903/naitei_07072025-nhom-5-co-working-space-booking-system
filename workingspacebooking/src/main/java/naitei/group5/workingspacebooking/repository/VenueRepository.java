package naitei.group5.workingspacebooking.repository;

import naitei.group5.workingspacebooking.entity.Venue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;


@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>, JpaSpecificationExecutor<Venue> {

    // Tìm venue theo id, load kèm owner + venueStyle → bỏ delete
    @Query("""
            SELECT v 
            FROM Venue v 
            JOIN FETCH v.owner 
            JOIN FETCH v.venueStyle 
            WHERE v.id = :id
              AND v.deleted = false
            """)
    Optional<Venue> findByIdWithDetails(@Param("id") Integer id);

    // Tìm tất cả venue của 1 owner → bỏ deleted
    @EntityGraph(attributePaths = {"venueStyle"})
    List<Venue> findByOwnerIdAndDeletedFalse(Integer ownerId);

    // Tìm tất cả venue đã verified → bỏ venue đã soft delete
    List<Venue> findByVerifiedTrueAndDeletedAtIsNull();

    // Xem cụ thể venue của 1 owner → bỏ venue deleted
    @Query("""
        SELECT DISTINCT v
        FROM Venue v
        JOIN FETCH v.owner o
        JOIN FETCH v.venueStyle vs
        LEFT JOIN FETCH v.prices p
        WHERE v.id = :venueId
          AND o.id = :ownerId
          AND v.deleted = false
        """)
    Optional<Venue> findByIdAndOwnerIdWithAllDetails(@Param("venueId") Integer venueId,
                                                     @Param("ownerId") Integer ownerId);

    @Modifying
    @Query("""
          UPDATE Venue v
          SET v.deletedAt = CURRENT_TIMESTAMP
          WHERE v.id = :venueId
          AND v.owner.id = :ownerId
        """)
    int softDeleteByIdAndOwnerId(@Param("venueId") Integer venueId,
                                 @Param("ownerId") Integer ownerId);

    long count(); // tổng venue
    long countByVerifiedTrue(); // venue đã verify
    long countByDeletedTrue();  // venue đã xoá mềm
    // Admin: Lấy venue với đầy đủ thông tin cho admin detail view
    @Query("""
        SELECT DISTINCT v
        FROM Venue v
        JOIN FETCH v.owner o
        JOIN FETCH v.venueStyle vs
        LEFT JOIN FETCH v.prices p
        WHERE v.id = :id
          AND v.deleted = false
        """)
    Optional<Venue> findByIdWithAllDetails(@Param("id") Integer id);

}
