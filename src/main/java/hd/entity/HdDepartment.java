package hd.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "helpdesk_deparment")
@Data
public class HdDepartment {
    @Id
    @Column(name = "deparment_id")
    @GenericGenerator(name = "u_id", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "u_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="empresa_id")
    private HdEmpresas hdempresas;

    private String descripcion;

}
