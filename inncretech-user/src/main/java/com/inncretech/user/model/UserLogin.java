package com.inncretech.user.model;



import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.Basic;
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

import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;
import org.joda.time.DateTime;


@Entity
@Table
public class UserLogin implements Serializable{


	private Address address;
	private String cellPhone;
	private DateTime createdAt;
	private Long createdBy;
	private String displayName;
	private DateTime dob;
	private String email;
	private String firstName;
	private String gender;
	private Long id = 0L; // init for hibernate bug workaround
	private ImageSource image;
	private String landLine;
	private String login;
	private String lastName;
	private Long memberId;
	private Byte memberLevel;
	private String middleInitial;
	private String phone;
	private Integer points;
	private Byte recordStatus;
	private Long refSecretQuestion1Id;
	private Long refSecretQuestion2Id;
	private Long refSecretQuestion3Id;
	private String secretAnswer1Hash;
	private String secretAnswer2Hash;
	private String secretAnswer3Hash;
	private String shortBio;
	private String statusUpdate;
	private Integer totalFollowers;
	private Integer totalFollowing;
	private Integer totalInterests;
	private Integer totalMagazines;
	private Integer totalWall;
	private DateTime updatedAt;
	private Long updatedBy;
	private Long versionId;
	private String publicId;
	
	private String facebookId;
	private String twitterId;
	
	@Basic
	@Column(name= "facebook_id")
  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  @Basic
  @Column(name= "twitter_id")
  public String getTwitterId() {
    return twitterId;
  }

  public void setTwitterId(String twitterId) {
    this.twitterId = twitterId;
  }

  /**
	 * Default constructor, mainly for hibernate use.
	 */
	public UserLogin() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public UserLogin(Long id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param id Long object;
	 * @param memberId Long object;
	 * @param updatedAt DateTime object;
	 * @param versionId Long object;
	 */
	public UserLogin(Long id, Long memberId, DateTime updatedAt, 					
			Long versionId) {

		this.id = id;
		this.memberId = memberId;
		this.updatedAt = updatedAt;
		this.versionId = versionId;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return UserLogin.class;
	}
 

    /**
     * Return the value associated with the column: address.
	 * @return A Address object (this.address)
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@Basic( optional = true )
	@JoinColumn(name = "address_id", nullable = true )
	public Address getAddress() {
		return this.address;
		
	}
	

  
    /**  
     * Set the value related to the column: address.
	 * @param address the address value you wish to set
	 */
	public void setAddress(final Address address) {
		this.address = address;
	}

    /**
     * Return the value associated with the column: cellPhone.
	 * @return A String object (this.cellPhone)
	 */
	@Basic( optional = true )
	@Column( name = "cell_phone", length = 15  )
	public String getCellPhone() {
		return this.cellPhone;
		
	}
	
	@Basic( optional = true )
  @Column( name = "public_id", length = 256)
  public String getPublicId() {
    return publicId;
  }

  public void setPublicId(String publicId) {
    this.publicId = publicId;
  }


  
    /**  
     * Set the value related to the column: cellPhone.
	 * @param cellPhone the cellPhone value you wish to set
	 */
	public void setCellPhone(final String cellPhone) {
		this.cellPhone = cellPhone;
	}

    /**
     * Return the value associated with the column: createdAt.
	 * @return A DateTime object (this.createdAt)
	 */
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = true )
	@Column( name = "created_at"  )
	public DateTime getCreatedAt() {
		return this.createdAt;
		
	}
	

  
    /**  
     * Set the value related to the column: createdAt.
	 * @param createdAt the createdAt value you wish to set
	 */
	public void setCreatedAt(final DateTime createdAt) {
		this.createdAt = createdAt;
	}

    /**
     * Return the value associated with the column: createdBy.
	 * @return A Long object (this.createdBy)
	 */
	@Basic( optional = true )
	@Column( name = "created_by"  )
	public Long getCreatedBy() {
		return this.createdBy;
		
	}
	

  
    /**  
     * Set the value related to the column: createdBy.
	 * @param createdBy the createdBy value you wish to set
	 */
	public void setCreatedBy(final Long createdBy) {
		this.createdBy = createdBy;
	}

    /**
     * Return the value associated with the column: displayName.
	 * @return A String object (this.displayName)
	 */
	@Basic( optional = true )
	@Column( name = "display_name", length = 45  )
	public String getDisplayName() {
		return this.displayName;
		
	}
	

  
    /**  
     * Set the value related to the column: displayName.
	 * @param displayName the displayName value you wish to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

    /**
     * Return the value associated with the column: dob.
	 * @return A DateTime object (this.dob)
	 */
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	public DateTime getDob() {
		return this.dob;
		
	}
	

  
    /**  
     * Set the value related to the column: dob.
	 * @param dob the dob value you wish to set
	 */
	public void setDob(final DateTime dob) {
		this.dob = dob;
	}
	
    /**
     * Return the value associated with the column: email.
	 * @return A String object (this.email)
	 */
	@Basic( optional = true )
	@Column( length = 100  )
	public String getEmail() {
		return this.email;
		
	}
	

  
    /**  
     * Set the value related to the column: email.
	 * @param email the email value you wish to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

    /**
     * Return the value associated with the column: firstName.
	 * @return A String object (this.firstName)
	 */
	@Basic( optional = true )
	@Column( name = "first_name", length = 45  )
	public String getFirstName() {
		return this.firstName;
		
	}
	

  
    /**  
     * Set the value related to the column: firstName.
	 * @param firstName the firstName value you wish to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

    /**
     * Return the value associated with the column: gender.
	 * @return A String object (this.gender)
	 */
	@Basic( optional = true )
	@Column( length = 2  )
	public String getGender() {
		return this.gender;
		
	}
	

  
    /**  
     * Set the value related to the column: gender.
	 * @param gender the gender value you wish to set
	 */
	public void setGender(final String gender) {
		this.gender = gender;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A Long object (this.id)
	 */
    @Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic( optional = false )
	@Column( name = "id", nullable = false  )
	public Long getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final Long id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null || this.id == 0L) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

    /**
     * Return the value associated with the column: image.
	 * @return A ImageSource object (this.image)
	 */
	@ManyToOne( fetch = FetchType.EAGER )
	@Basic( optional = true )
	@JoinColumn(name = "image_id", nullable = true )
	public ImageSource getImage() {
		return this.image;
		
	}

	  
    /**  
     * Set the value related to the column: image.
	 * @param image the image value you wish to set
	 */
	public void setImage(final ImageSource image) {
		this.image = image;
	}


    /**
     * Return the value associated with the column: landLine.
	 * @return A String object (this.landLine)
	 */
	@Basic( optional = true )
	@Column( name = "land_line", length = 15  )
	public String getLandLine() {
		return this.landLine;
		
	}
	

  
    /**  
     * Set the value related to the column: landLine.
	 * @param landLine the landLine value you wish to set
	 */
	public void setLandLine(final String landLine) {
		this.landLine = landLine;
	}

    /**
     * Return the value associated with the column: lastName.
	 * @return A String object (this.lastName)
	 */
	@Basic( optional = true )
	@Column( name = "last_name", length = 45  )
	public String getLastName() {
		return this.lastName;
		
	}
	

  
    /**  
     * Set the value related to the column: lastName.
	 * @param lastName the lastName value you wish to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
    /**
     * Return the value associated with the column: login.
	 * @return A String object (this.login)
	 */
	@Basic( optional = true )
	@Column( length = 100  )
	public String getLogin() {
		return this.login;
		
	}
	
  
    /**  
     * Set the value related to the column: login.
	 * @param login the login value you wish to set
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

    /**
     * Return the value associated with the column: memberId.
	 * @return A Long object (this.memberId)
	 */
	@Basic( optional = false )
	@Column( name = "member_id", nullable = false  )
	public Long getMemberId() {
		return this.memberId;
		
	}
	

  
    /**  
     * Set the value related to the column: memberId.
	 * @param memberId the memberId value you wish to set
	 */
	public void setMemberId(final Long memberId) {
		this.memberId = memberId;
	}

    /**
     * Return the value associated with the column: memberLevel.
	 * @return A Byte object (this.memberLevel)
	 */
	@Basic( optional = true )
	@Column( name = "member_level"  )
	public Byte getMemberLevel() {
		return this.memberLevel;
		
	}
	

  
    /**  
     * Set the value related to the column: memberLevel.
	 * @param memberLevel the memberLevel value you wish to set
	 */
	public void setMemberLevel(final Byte memberLevel) {
		this.memberLevel = memberLevel;
	}

    /**
     * Return the value associated with the column: middleInitial.
	 * @return A String object (this.middleInitial)
	 */
	@Basic( optional = true )
	@Column( name = "middle_initial", length = 45  )
	public String getMiddleInitial() {
		return this.middleInitial;
		
	}
	

  
    /**  
     * Set the value related to the column: middleInitial.
	 * @param middleInitial the middleInitial value you wish to set
	 */
	public void setMiddleInitial(final String middleInitial) {
		this.middleInitial = middleInitial;
	}
	
    /**
     * Return the value associated with the column: phone.
	 * @return A String object (this.phone)
	 */
	@Basic( optional = true )
	@Column( length = 20  )
	public String getPhone() {
		return this.phone;
		
	}
	

  
    /**  
     * Set the value related to the column: phone.
	 * @param phone the phone value you wish to set
	 */
	public void setPhone(final String phone) {
		this.phone = phone;
	}

    /**
     * Return the value associated with the column: points.
	 * @return A Byte object (this.points)
	 */
	public Integer getPoints() {
		return this.points;
		
	}
	

  
    /**  
     * Set the value related to the column: points.
	 * @param i the points value you wish to set
	 */
	public void setPoints(final Integer i) {
		this.points = i;
	}

    /**
     * Return the value associated with the column: recordStatus.
	 * @return A Byte object (this.recordStatus)
	 */
	@Basic( optional = true )
	@Column( name = "record_status"  )
	public Byte getRecordStatus() {
		return this.recordStatus;
		
	}
	

  
    /**  
     * Set the value related to the column: recordStatus.
	 * @param recordStatus the recordStatus value you wish to set
	 */
	public void setRecordStatus(final Byte recordStatus) {
		this.recordStatus = recordStatus;
	}

    /**
     * Return the value associated with the column: refSecretQuestion1Id.
	 * @return A Long object (this.refSecretQuestion1Id)
	 */
	@Basic( optional = true )
	@Column( name = "ref_secret_question1_id"  )
	public Long getRefSecretQuestion1Id() {
		return this.refSecretQuestion1Id;
		
	}
	

  
    /**  
     * Set the value related to the column: refSecretQuestion1Id.
	 * @param refSecretQuestion1Id the refSecretQuestion1Id value you wish to set
	 */
	public void setRefSecretQuestion1Id(final Long refSecretQuestion1Id) {
		this.refSecretQuestion1Id = refSecretQuestion1Id;
	}

    /**
     * Return the value associated with the column: refSecretQuestion2Id.
	 * @return A Long object (this.refSecretQuestion2Id)
	 */
	@Basic( optional = true )
	@Column( name = "ref_secret_question2_id"  )
	public Long getRefSecretQuestion2Id() {
		return this.refSecretQuestion2Id;
		
	}
	

  
    /**  
     * Set the value related to the column: refSecretQuestion2Id.
	 * @param refSecretQuestion2Id the refSecretQuestion2Id value you wish to set
	 */
	public void setRefSecretQuestion2Id(final Long refSecretQuestion2Id) {
		this.refSecretQuestion2Id = refSecretQuestion2Id;
	}

    /**
     * Return the value associated with the column: refSecretQuestion3Id.
	 * @return A Long object (this.refSecretQuestion3Id)
	 */
	@Basic( optional = true )
	@Column( name = "ref_secret_question3_id"  )
	public Long getRefSecretQuestion3Id() {
		return this.refSecretQuestion3Id;
		
	}
	

  
    /**  
     * Set the value related to the column: refSecretQuestion3Id.
	 * @param refSecretQuestion3Id the refSecretQuestion3Id value you wish to set
	 */
	public void setRefSecretQuestion3Id(final Long refSecretQuestion3Id) {
		this.refSecretQuestion3Id = refSecretQuestion3Id;
	}

    /**
     * Return the value associated with the column: secretAnswer1Hash.
	 * @return A String object (this.secretAnswer1Hash)
	 */
	@Basic( optional = true )
	@Column( name = "secret_answer1_hash", length = 128  )
	public String getSecretAnswer1Hash() {
		return this.secretAnswer1Hash;
		
	}
	

  
    /**  
     * Set the value related to the column: secretAnswer1Hash.
	 * @param secretAnswer1Hash the secretAnswer1Hash value you wish to set
	 */
	public void setSecretAnswer1Hash(final String secretAnswer1Hash) {
		this.secretAnswer1Hash = secretAnswer1Hash;
	}

    /**
     * Return the value associated with the column: secretAnswer2Hash.
	 * @return A String object (this.secretAnswer2Hash)
	 */
	@Basic( optional = true )
	@Column( name = "secret_answer2_hash", length = 128  )
	public String getSecretAnswer2Hash() {
		return this.secretAnswer2Hash;
		
	}
	

  
    /**  
     * Set the value related to the column: secretAnswer2Hash.
	 * @param secretAnswer2Hash the secretAnswer2Hash value you wish to set
	 */
	public void setSecretAnswer2Hash(final String secretAnswer2Hash) {
		this.secretAnswer2Hash = secretAnswer2Hash;
	}

    /**
     * Return the value associated with the column: secretAnswer3Hash.
	 * @return A String object (this.secretAnswer3Hash)
	 */
	@Basic( optional = true )
	@Column( name = "secret_answer3_hash", length = 128  )
	public String getSecretAnswer3Hash() {
		return this.secretAnswer3Hash;
		
	}
	

  
    /**  
     * Set the value related to the column: secretAnswer3Hash.
	 * @param secretAnswer3Hash the secretAnswer3Hash value you wish to set
	 */
	public void setSecretAnswer3Hash(final String secretAnswer3Hash) {
		this.secretAnswer3Hash = secretAnswer3Hash;
	}
	
	/**
     * Return the value associated with the column: shortBio.
	 * @return A String object (this.shortBio)
	 */
	@Basic( optional = true )
	@Column( name = "short_bio", length = 256  )
	public String getShortBio() {
		return this.shortBio;
		
	}
	

  
    /**  
     * Set the value related to the column: shortBio.
	 * @param shortBio the shortBio value you wish to set
	 */
	public void setShortBio(final String shortBio) {
		this.shortBio = shortBio;
	}

    /**
     * Return the value associated with the column: statusUpdate.
	 * @return A String object (this.statusUpdate)
	 */
	@Basic( optional = true )
	@Column( name = "status_update", length = 256  )
	public String getStatusUpdate() {
		return this.statusUpdate;
		
	}
	

  
    /**  
     * Set the value related to the column: statusUpdate.
	 * @param statusUpdate the statusUpdate value you wish to set
	 */
	public void setStatusUpdate(final String statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

    /**
     * Return the value associated with the column: totalFollowers.
	 * @return A Long object (this.totalFollowers)
	 */
	@Basic( optional = true )
	@Column( name = "total_followers"  )
	public Integer getTotalFollowers() {
		return this.totalFollowers;
		
	}
	

  
    /**  
     * Set the value related to the column: totalFollowers.
	 * @param totalFollowers the totalFollowers value you wish to set
	 */
	public void setTotalFollowers(final Integer totalFollowers) {
		this.totalFollowers = totalFollowers;
	}

    /**
     * Return the value associated with the column: totalFollowing.
	 * @return A Long object (this.totalFollowing)
	 */
	@Basic( optional = true )
	@Column( name = "total_following"  )
	public Integer getTotalFollowing() {
		return this.totalFollowing;
		
	}
	

  
    /**  
     * Set the value related to the column: totalFollowing.
	 * @param totalFollowing the totalFollowing value you wish to set
	 */
	public void setTotalFollowing(final Integer totalFollowing) {
		this.totalFollowing = totalFollowing;
	}

    /**
     * Return the value associated with the column: totalInterests.
	 * @return A Long object (this.totalInterests)
	 */
	@Basic( optional = true )
	@Column( name = "total_interests"  )
	public Integer getTotalInterests() {
		return this.totalInterests;
		
	}
	

  
    /**  
     * Set the value related to the column: totalInterests.
	 * @param totalInterests the totalInterests value you wish to set
	 */
	public void setTotalInterests(final Integer totalInterests) {
		this.totalInterests = totalInterests;
	}

    /**
     * Return the value associated with the column: totalMagazines.
	 * @return A Long object (this.totalMagazines)
	 */
	@Basic( optional = true )
	@Column( name = "total_magazines"  )
	public Integer getTotalMagazines() {
		return this.totalMagazines;
		
	}
	

  
    /**  
     * Set the value related to the column: totalMagazines.
	 * @param totalMagazines the totalMagazines value you wish to set
	 */
	public void setTotalMagazines(final Integer totalMagazines) {
		this.totalMagazines = totalMagazines;
	}

    /**
     * Return the value associated with the column: totalWall.
	 * @return A Long object (this.totalWall)
	 */
	@Basic( optional = true )
	@Column( name = "total_wall"  )
	public Integer getTotalWall() {
		return this.totalWall;
		
	}
	

  
    /**  
     * Set the value related to the column: totalWall.
	 * @param totalWall the totalWall value you wish to set
	 */
	public void setTotalWall(final Integer totalWall) {
		this.totalWall = totalWall;
	}

    /**
     * Return the value associated with the column: updatedAt.
	 * @return A DateTime object (this.updatedAt)
	 */
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@Basic( optional = false )
	@Column( name = "updated_at", nullable = false  )
	public DateTime getUpdatedAt() {
		return this.updatedAt;
		
	}
	

  
    /**  
     * Set the value related to the column: updatedAt.
	 * @param updatedAt the updatedAt value you wish to set
	 */
	public void setUpdatedAt(final DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

    /**
     * Return the value associated with the column: updatedBy.
	 * @return A Long object (this.updatedBy)
	 */
	@Basic( optional = true )
	@Column( name = "updated_by"  )
	public Long getUpdatedBy() {
		return this.updatedBy;
		
	}
	

  
    /**  
     * Set the value related to the column: updatedBy.
	 * @param updatedBy the updatedBy value you wish to set
	 */
	public void setUpdatedBy(final Long updatedBy) {
		this.updatedBy = updatedBy;
	}

    /**
     * Return the value associated with the column: versionId.
	 * @return A Long object (this.versionId)
	 */
	@Basic( optional = false )
	@Column( name = "version_id", nullable = false  )
	public Long getVersionId() {
		return this.versionId;
		
	}
	

  
    /**  
     * Set the value related to the column: versionId.
	 * @param versionId the versionId value you wish to set
	 */
	public void setVersionId(final Long versionId) {
		this.versionId = versionId;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public UserLogin clone() throws CloneNotSupportedException {
		
        final UserLogin copy = (UserLogin)super.clone();

		copy.setAddress(this.getAddress());
		copy.setCellPhone(this.getCellPhone());
		copy.setCreatedAt(this.getCreatedAt());
		copy.setCreatedBy(this.getCreatedBy());
		copy.setDisplayName(this.getDisplayName());
		copy.setDob(this.getDob());
		copy.setEmail(this.getEmail());
		copy.setFirstName(this.getFirstName());
		copy.setGender(this.getGender());
		copy.setId(this.getId());
		copy.setImage(this.getImage());
		copy.setLandLine(this.getLandLine());
		copy.setLastName(this.getLastName());
		copy.setLogin(this.getLogin());
		copy.setMemberId(this.getMemberId());
		copy.setMemberLevel(this.getMemberLevel());
		copy.setMiddleInitial(this.getMiddleInitial());
		copy.setPhone(this.getPhone());
		copy.setPoints(this.getPoints());
		copy.setRecordStatus(this.getRecordStatus());
		copy.setRefSecretQuestion1Id(this.getRefSecretQuestion1Id());
		copy.setRefSecretQuestion2Id(this.getRefSecretQuestion2Id());
		copy.setRefSecretQuestion3Id(this.getRefSecretQuestion3Id());
		copy.setSecretAnswer1Hash(this.getSecretAnswer1Hash());
		copy.setSecretAnswer2Hash(this.getSecretAnswer2Hash());
		copy.setSecretAnswer3Hash(this.getSecretAnswer3Hash());
		copy.setShortBio(this.getShortBio());
		copy.setStatusUpdate(this.getStatusUpdate());
		copy.setTotalFollowers(this.getTotalFollowers());
		copy.setTotalFollowing(this.getTotalFollowing());
		copy.setTotalInterests(this.getTotalInterests());
		copy.setTotalMagazines(this.getTotalMagazines());
		copy.setTotalWall(this.getTotalWall());
		copy.setUpdatedAt(this.getUpdatedAt());
		copy.setUpdatedBy(this.getUpdatedBy());
		copy.setVersionId(this.getVersionId());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("address: " + this.getAddress() + ", ");
		sb.append("cellPhone: " + this.getCellPhone() + ", ");
		sb.append("createdAt: " + this.getCreatedAt() + ", ");
		sb.append("createdBy: " + this.getCreatedBy() + ", ");
		sb.append("displayName: " + this.getDisplayName() + ", ");
		sb.append("dob: " + this.getDob() + ", ");
		sb.append("firstName: " + this.getFirstName() + ", ");
		sb.append("gender: " + this.getGender() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("image: " + this.getImage() + ", ");
		sb.append("landLine: " + this.getLandLine() + ", ");
		sb.append("lastName: " + this.getLastName() + ", ");
		sb.append("memberId: " + this.getMemberId() + ", ");
		sb.append("memberLevel: " + this.getMemberLevel() + ", ");
		sb.append("middleInitial: " + this.getMiddleInitial() + ", ");
		sb.append("points: " + this.getPoints() + ", ");
		sb.append("recordStatus: " + this.getRecordStatus() + ", ");
		sb.append("refSecretQuestion1Id: " + this.getRefSecretQuestion1Id() + ", ");
		sb.append("refSecretQuestion2Id: " + this.getRefSecretQuestion2Id() + ", ");
		sb.append("refSecretQuestion3Id: " + this.getRefSecretQuestion3Id() + ", ");
		sb.append("secretAnswer1Hash: " + this.getSecretAnswer1Hash() + ", ");
		sb.append("secretAnswer2Hash: " + this.getSecretAnswer2Hash() + ", ");
		sb.append("secretAnswer3Hash: " + this.getSecretAnswer3Hash() + ", ");
		sb.append("statusUpdate: " + this.getStatusUpdate() + ", ");
		sb.append("totalFollowers: " + this.getTotalFollowers() + ", ");
		sb.append("totalFollowing: " + this.getTotalFollowing() + ", ");
		sb.append("totalInterests: " + this.getTotalInterests() + ", ");
		sb.append("totalMagazines: " + this.getTotalMagazines() + ", ");
		sb.append("totalWall: " + this.getTotalWall() + ", ");
		sb.append("updatedAt: " + this.getUpdatedAt() + ", ");
		sb.append("updatedBy: " + this.getUpdatedBy() + ", ");
		sb.append("versionId: " + this.getVersionId());
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final UserLogin that; 
		try {
			that = (UserLogin) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getAddress() == null) && (that.getAddress() == null)) || (getAddress() != null && getAddress().getId().equals(that.getAddress().getId())));
		result = result && (((getCellPhone() == null) && (that.getCellPhone() == null)) || (getCellPhone() != null && getCellPhone().equals(that.getCellPhone())));
		result = result && (((getCreatedAt() == null) && (that.getCreatedAt() == null)) || (getCreatedAt() != null && getCreatedAt().equals(that.getCreatedAt())));
		result = result && (((getCreatedBy() == null) && (that.getCreatedBy() == null)) || (getCreatedBy() != null && getCreatedBy().equals(that.getCreatedBy())));
		result = result && (((getDisplayName() == null) && (that.getDisplayName() == null)) || (getDisplayName() != null && getDisplayName().equals(that.getDisplayName())));
		result = result && (((getDob() == null) && (that.getDob() == null)) || (getDob() != null && getDob().equals(that.getDob())));
		result = result && (((getEmail() == null) && (that.getEmail() == null)) || (getEmail() != null && getEmail().equals(that.getEmail())));
		result = result && (((getFirstName() == null) && (that.getFirstName() == null)) || (getFirstName() != null && getFirstName().equals(that.getFirstName())));
		result = result && (((getGender() == null) && (that.getGender() == null)) || (getGender() != null && getGender().equals(that.getGender())));
		result = result && (((getImage() == null) && (that.getImage() == null)) || (getImage() != null && getImage().getId().equals(that.getImage().getId())));	
		result = result && (((getLandLine() == null) && (that.getLandLine() == null)) || (getLandLine() != null && getLandLine().equals(that.getLandLine())));
		result = result && (((getLastName() == null) && (that.getLastName() == null)) || (getLastName() != null && getLastName().equals(that.getLastName())));
		result = result && (((getLogin() == null) && (that.getLogin() == null)) || (getLogin() != null && getLogin().equals(that.getLogin())));
		result = result && (((getMemberId() == null) && (that.getMemberId() == null)) || (getMemberId() != null && getMemberId().equals(that.getMemberId())));
		result = result && (((getMemberLevel() == null) && (that.getMemberLevel() == null)) || (getMemberLevel() != null && getMemberLevel().equals(that.getMemberLevel())));
		result = result && (((getMiddleInitial() == null) && (that.getMiddleInitial() == null)) || (getMiddleInitial() != null && getMiddleInitial().equals(that.getMiddleInitial())));
		result = result && (((getPhone() == null) && (that.getPhone() == null)) || (getPhone() != null && getPhone().equals(that.getPhone())));
		result = result && (((getPoints() == null) && (that.getPoints() == null)) || (getPoints() != null && getPoints().equals(that.getPoints())));
		result = result && (((getRecordStatus() == null) && (that.getRecordStatus() == null)) || (getRecordStatus() != null && getRecordStatus().equals(that.getRecordStatus())));
		result = result && (((getRefSecretQuestion1Id() == null) && (that.getRefSecretQuestion1Id() == null)) || (getRefSecretQuestion1Id() != null && getRefSecretQuestion1Id().equals(that.getRefSecretQuestion1Id())));
		result = result && (((getRefSecretQuestion2Id() == null) && (that.getRefSecretQuestion2Id() == null)) || (getRefSecretQuestion2Id() != null && getRefSecretQuestion2Id().equals(that.getRefSecretQuestion2Id())));
		result = result && (((getRefSecretQuestion3Id() == null) && (that.getRefSecretQuestion3Id() == null)) || (getRefSecretQuestion3Id() != null && getRefSecretQuestion3Id().equals(that.getRefSecretQuestion3Id())));
		result = result && (((getSecretAnswer1Hash() == null) && (that.getSecretAnswer1Hash() == null)) || (getSecretAnswer1Hash() != null && getSecretAnswer1Hash().equals(that.getSecretAnswer1Hash())));
		result = result && (((getSecretAnswer2Hash() == null) && (that.getSecretAnswer2Hash() == null)) || (getSecretAnswer2Hash() != null && getSecretAnswer2Hash().equals(that.getSecretAnswer2Hash())));
		result = result && (((getSecretAnswer3Hash() == null) && (that.getSecretAnswer3Hash() == null)) || (getSecretAnswer3Hash() != null && getSecretAnswer3Hash().equals(that.getSecretAnswer3Hash())));
		result = result && (((getShortBio() == null) && (that.getShortBio() == null)) || (getShortBio() != null && getShortBio().equals(that.getShortBio())));
		result = result && (((getStatusUpdate() == null) && (that.getStatusUpdate() == null)) || (getStatusUpdate() != null && getStatusUpdate().equals(that.getStatusUpdate())));
		result = result && (((getTotalFollowers() == null) && (that.getTotalFollowers() == null)) || (getTotalFollowers() != null && getTotalFollowers().equals(that.getTotalFollowers())));
		result = result && (((getTotalFollowing() == null) && (that.getTotalFollowing() == null)) || (getTotalFollowing() != null && getTotalFollowing().equals(that.getTotalFollowing())));
		result = result && (((getTotalInterests() == null) && (that.getTotalInterests() == null)) || (getTotalInterests() != null && getTotalInterests().equals(that.getTotalInterests())));
		result = result && (((getTotalMagazines() == null) && (that.getTotalMagazines() == null)) || (getTotalMagazines() != null && getTotalMagazines().equals(that.getTotalMagazines())));
		result = result && (((getTotalWall() == null) && (that.getTotalWall() == null)) || (getTotalWall() != null && getTotalWall().equals(that.getTotalWall())));
		result = result && (((getUpdatedAt() == null) && (that.getUpdatedAt() == null)) || (getUpdatedAt() != null && getUpdatedAt().equals(that.getUpdatedAt())));
		result = result && (((getUpdatedBy() == null) && (that.getUpdatedBy() == null)) || (getUpdatedBy() != null && getUpdatedBy().equals(that.getUpdatedBy())));
		result = result && (((getVersionId() == null) && (that.getVersionId() == null)) || (getVersionId() != null && getVersionId().equals(that.getVersionId())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					Long newHashCode = null;

					if ( getId() != null ) {
					newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null && getId() != 0L) {
							newHashCode = getId();
						} else {
							newHashCode = (long) super.hashCode();

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
		return (int) (this.hashCode & 0xffffff);
	}
	

	
}
