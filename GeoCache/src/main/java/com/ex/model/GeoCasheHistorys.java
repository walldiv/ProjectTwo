package com.ex.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author that-team
 *
 * This class allows us to see the history of this geocache location. We will be able to see what items have been placed and
 * retrieved from this geocache. We can see when transactions have occured, and we can see any comments left by users.
 *
 * @param email - Allows to associate transactions with a user
 * @param itemID - this is misnamed. It actually refers to the geocacheId
 * @param date_collected - the date of the transaction (either placing an id or retrieving)
 * @param comment - the comment can be left by a user to help convey some info to other users
 * @param rating - upon finding the geocache, a user can rate it; this information can help inform other users
 *               about the difficulty of the location; this information will also help with the badge system (tbd)
 *
 * @author Jordan Severance
 * @author Daniel Wallace
 */
@Entity
@Table(name = "\"GeoCasheHistorys\"",schema = "\"that-team_schema\"")
public class GeoCasheHistorys implements Serializable {
    @Id
    @Column(name = "email")
    private String email;

    //this is geocache id
    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private GeoCashe itemID;

    @Column(name = "date_collected")
    private LocalDateTime dateCollected;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private int rating;

    public GeoCasheHistorys(){}

    public GeoCasheHistorys(String email, GeoCashe itemID, LocalDateTime dateCollected, String comment, int rating) {
        this.email = email;
        this.itemID = itemID;
        this.dateCollected = dateCollected;
        this.comment = comment;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GeoCashe getItemID() {
        return itemID;
    }

    public void setItemID(GeoCashe itemID) {
        this.itemID = itemID;
    }

    public LocalDateTime getDateCollected() {
        return dateCollected;
    }

    public void setDateCollected(LocalDateTime dateCollected) {
        this.dateCollected = dateCollected;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
