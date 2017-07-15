package interviu.alex.server.service.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alexa on 7/11/2017.
 */
@Entity
@Table(name = "PHOTO")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name = "PICTURE_REF")
    private String pictureRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLACE_ID")
    private PlaceEntity place;

    public PhotoEntity() {
    }

    public PhotoEntity(String pictureRef) {
        this.pictureRef = pictureRef;
    }

    public PhotoEntity(String pictureRef, PlaceEntity place) {
        this.pictureRef = pictureRef;
        this.place = place;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPictureRef() {
        return pictureRef;
    }

    public void setPictureRef(String pictureRef) {
        this.pictureRef = pictureRef;
    }

    public PlaceEntity getPlace() {
        return place;
    }

    public void setPlace(PlaceEntity place) {
        this.place = place;
    }
}
