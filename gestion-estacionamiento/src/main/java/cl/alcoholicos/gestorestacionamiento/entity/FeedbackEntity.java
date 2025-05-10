package cl.alcoholicos.gestorestacionamiento.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FEEDBACK")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FEEDBACK")
    private int idFeedback;
    @Column(name = "FECHA_FEEDBACK")
    private Date fechaFeedback;
    @Column(name = "DESC_FEEDBACK")
    private String descFeedback;
    @Column(name = "RUT_USUARIO")
    private int rutUsuario;

}
