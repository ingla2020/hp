package hd.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "helpdesk_empresa")
@Data
public class HdEmpresas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descripcion;

    private String codigo;


}
