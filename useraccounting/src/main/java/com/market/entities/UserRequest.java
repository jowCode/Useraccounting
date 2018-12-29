package com.market.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "UserRequest")
@Table(name = "user_request")
public class UserRequest {
  
  /************************************************************
   * Unique ID for each request.*******************************
   ************************************************************
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  
  @Column(name = "title", updatable = false)
  private String title;
  
  @Column(name = "description", columnDefinition = "TEXT", length = 400, updatable = false)
  private String description;
  
  @Column(name = "urgency", updatable = false)
  private String urgency;
  
  @Column(updatable = false, nullable = false)
  private Timestamp timestamp;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;
  
  @Transient //indicates that this variable should not be used as a column in sql
  private String elapsedtime;
  
  
  public String getElapsedtime() {
    return this.elapsedtime;
  }
  
  /**
   * Set the elapsed time as a good readable string.
   */
  public void setElapsedtime() {
    
    long start = this.timestamp.getTime();
    /**
     * Workaraound to get the current Timestamp.
     */
    Calendar calendar = Calendar.getInstance();
    Date now = calendar.getTime();
    Timestamp finishAsTimestamp = new Timestamp(now.getTime());
    long finish = finishAsTimestamp.getTime();
    
    long elapsedTimeInMillis = finish - start;
    
    /**
     * Defining the thresholds for minute hour day.
     */
    long minuteInMillis = 60 * 1000;
    long hourInMillis = minuteInMillis * 60;
    long dayInMillis = hourInMillis * 24;
    
    if (elapsedTimeInMillis <= minuteInMillis) {
      /**
       *TODO replace text by property file key
       * elapsed seconds.
       */
      this.elapsedtime = "some seconds ago";  
    
    } else if ((elapsedTimeInMillis > minuteInMillis) && (elapsedTimeInMillis <= hourInMillis)) {
      /**
       * TODO replace text by property file key
       * elapsed minutes.
       */
      Integer minutes = (int) (elapsedTimeInMillis / minuteInMillis) % 60;
      this.elapsedtime = minutes.toString() + " mintues ago";
    
    } else if ((elapsedTimeInMillis > hourInMillis) && (elapsedTimeInMillis <= dayInMillis)) {
      /**
       * TODO replace text by property file key
       * elapsed hours.
       */
      Integer hours = (int) (elapsedTimeInMillis / dayInMillis) % 24;
      this.elapsedtime = "about " + hours.toString() + " hours ago";
    } else if (elapsedTimeInMillis > dayInMillis) {
      /**
       * TODO replace text by property file key
       * elapsed days.
       */
      Integer days = (int) (elapsedTimeInMillis / dayInMillis) % 24;
      this.elapsedtime = "about" + days.toString() + " days ago";
    } else {
      this.elapsedtime = "something went wrong";
    }
  }
  
  /**standard getters and setters and equals plus hashcode.*/
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getUrgency() {
    return urgency;
  }

  public void setUrgency(final String urgency) {
    this.urgency = urgency;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(final Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((elapsedtime == null) ? 0 : elapsedtime.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((urgency == null) ? 0 : urgency.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof UserRequest)) {
      return false;
    }
    UserRequest other = (UserRequest) obj;
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (elapsedtime == null) {
      if (other.elapsedtime != null) {
        return false;
      }
    } else if (!elapsedtime.equals(other.elapsedtime)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (timestamp == null) {
      if (other.timestamp != null) {
        return false;
      }
    } else if (!timestamp.equals(other.timestamp)) {
      return false;
    }
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    if (urgency == null) {
      if (other.urgency != null) {
        return false;
      }
    } else if (!urgency.equals(other.urgency)) {
      return false;
    }
    if (user == null) {
      if (other.user != null) {
        return false;
      }
    } else if (!user.equals(other.user)) {
      return false;
    }
    return true;
  }


}
