package com.Upcoming.Events.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date_hour")
    private String date_hour;

    @Column(name = "max_participants")
    private int max_participants;

    @Column(name = "description")
    private String description;

    @Column(name = "style")
    private String style;

    @Column(name = "actual_participants")
    private int actual_participants;

}
