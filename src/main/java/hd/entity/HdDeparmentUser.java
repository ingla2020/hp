package hd.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "helpdesk_deparment_user")
@Data
public class HdDeparmentUser {
    @Id
    @Column(name = "user_id")
    @GenericGenerator(name = "u_id", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "u_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empresa_id")
    private HdEmpresas hdempresas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deparment_id")
    private HdDepartment hddepartment;

    private String descripcion;
    private String email;
    private String passwordEn;

}
