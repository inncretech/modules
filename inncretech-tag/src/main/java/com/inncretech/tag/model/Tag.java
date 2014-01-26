package com.inncretech.tag.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.inncretech.core.model.AbstractMutableEntity;

@Entity
public class Tag extends AbstractMutableEntity {

  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column
  private String name;

  @Column
  private Long imageId;

  @Column
  private Long totalFollowers;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getTotalFollowers() {
    return totalFollowers;
  }

  public void setTotalFollowers(Long totalFollowers) {
    this.totalFollowers = totalFollowers;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((imageId == null) ? 0 : imageId.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((totalFollowers == null) ? 0 : totalFollowers.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tag other = (Tag) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (imageId == null) {
      if (other.imageId != null)
        return false;
    } else if (!imageId.equals(other.imageId))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (totalFollowers == null) {
      if (other.totalFollowers != null)
        return false;
    } else if (!totalFollowers.equals(other.totalFollowers))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Tag [id=" + id + ", name=" + name + ", imageId=" + imageId + ", totalFollowers=" + totalFollowers + ", toString()=" + super.toString()
        + "]";
  }

  public Long getImageId() {
    return imageId;
  }

  public void setImageId(Long imageId) {
    this.imageId = imageId;
  }
}
