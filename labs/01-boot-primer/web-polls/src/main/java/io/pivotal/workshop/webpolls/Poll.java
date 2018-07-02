package io.pivotal.workshop.webpolls;

import java.util.Date;

public class Poll {
  private Date created;
  private int candidate1, candidate2;

  Poll() {
    this.created = new Date();
  }

  Poll(int a, int b) {
    this();
    this.candidate1 = a;
    this.candidate2 = b;
  }

  public int getCandidate1() {
    return candidate1;
  }

  public int getCandidate2() {
    return candidate2;
  }

  public Date getCreated() {
    return created;
  }

}
