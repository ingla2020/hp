package hd.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;


@Entity
@Table(name = "helpdesk_empresa")
@Data
public class HdEmpresas {

    @Id
    @Column(name = "empresa_id")
    @GenericGenerator(name = "u_id", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "u_id")
    private String id;
    private String descripcion;
    private String email;
    private String passwordEn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hdempresas")
    private List<HdDepartment> hddeparment;

}
