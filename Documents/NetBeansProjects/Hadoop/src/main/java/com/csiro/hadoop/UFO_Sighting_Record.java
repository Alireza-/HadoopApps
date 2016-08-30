package com.csiro.hadoop;

/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class UFO_Sighting_Record extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UFO_Sighting_Record\",\"fields\":[{\"name\":\"sighting_date\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"shape\",\"type\":[\"null\",\"string\"]},{\"name\":\"duration\",\"type\":\"double\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence sighting_date;
  @Deprecated public java.lang.CharSequence city;
  @Deprecated public java.lang.CharSequence shape;
  @Deprecated public double duration;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public UFO_Sighting_Record() {}

  /**
   * All-args constructor.
   */
  public UFO_Sighting_Record(java.lang.CharSequence sighting_date, java.lang.CharSequence city, java.lang.CharSequence shape, java.lang.Double duration) {
    this.sighting_date = sighting_date;
    this.city = city;
    this.shape = shape;
    this.duration = duration;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sighting_date;
    case 1: return city;
    case 2: return shape;
    case 3: return duration;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sighting_date = (java.lang.CharSequence)value$; break;
    case 1: city = (java.lang.CharSequence)value$; break;
    case 2: shape = (java.lang.CharSequence)value$; break;
    case 3: duration = (java.lang.Double)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'sighting_date' field.
   */
  public java.lang.CharSequence getSightingDate() {
    return sighting_date;
  }

  /**
   * Sets the value of the 'sighting_date' field.
   * @param value the value to set.
   */
  public void setSightingDate(java.lang.CharSequence value) {
    this.sighting_date = value;
  }

  /**
   * Gets the value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }

  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'shape' field.
   */
  public java.lang.CharSequence getShape() {
    return shape;
  }

  /**
   * Sets the value of the 'shape' field.
   * @param value the value to set.
   */
  public void setShape(java.lang.CharSequence value) {
    this.shape = value;
  }

  /**
   * Gets the value of the 'duration' field.
   */
  public java.lang.Double getDuration() {
    return duration;
  }

  /**
   * Sets the value of the 'duration' field.
   * @param value the value to set.
   */
  public void setDuration(java.lang.Double value) {
    this.duration = value;
  }

  /** Creates a new UFO_Sighting_Record RecordBuilder */
  public static UFO_Sighting_Record.Builder newBuilder() {
    return new UFO_Sighting_Record.Builder();
  }
  
  /** Creates a new UFO_Sighting_Record RecordBuilder by copying an existing Builder */
  public static UFO_Sighting_Record.Builder newBuilder(UFO_Sighting_Record.Builder other) {
    return new UFO_Sighting_Record.Builder(other);
  }
  
  /** Creates a new UFO_Sighting_Record RecordBuilder by copying an existing UFO_Sighting_Record instance */
  public static UFO_Sighting_Record.Builder newBuilder(UFO_Sighting_Record other) {
    return new UFO_Sighting_Record.Builder(other);
  }
  
  /**
   * RecordBuilder for UFO_Sighting_Record instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UFO_Sighting_Record>
    implements org.apache.avro.data.RecordBuilder<UFO_Sighting_Record> {

    private java.lang.CharSequence sighting_date;
    private java.lang.CharSequence city;
    private java.lang.CharSequence shape;
    private double duration;

    /** Creates a new Builder */
    private Builder() {
      super(UFO_Sighting_Record.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(UFO_Sighting_Record.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sighting_date)) {
        this.sighting_date = data().deepCopy(fields()[0].schema(), other.sighting_date);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.shape)) {
        this.shape = data().deepCopy(fields()[2].schema(), other.shape);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.duration)) {
        this.duration = data().deepCopy(fields()[3].schema(), other.duration);
        fieldSetFlags()[3] = true;
      }
    }
    
    /** Creates a Builder by copying an existing UFO_Sighting_Record instance */
    private Builder(UFO_Sighting_Record other) {
            super(UFO_Sighting_Record.SCHEMA$);
      if (isValidValue(fields()[0], other.sighting_date)) {
        this.sighting_date = data().deepCopy(fields()[0].schema(), other.sighting_date);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.shape)) {
        this.shape = data().deepCopy(fields()[2].schema(), other.shape);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.duration)) {
        this.duration = data().deepCopy(fields()[3].schema(), other.duration);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'sighting_date' field */
    public java.lang.CharSequence getSightingDate() {
      return sighting_date;
    }
    
    /** Sets the value of the 'sighting_date' field */
    public UFO_Sighting_Record.Builder setSightingDate(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.sighting_date = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'sighting_date' field has been set */
    public boolean hasSightingDate() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'sighting_date' field */
    public UFO_Sighting_Record.Builder clearSightingDate() {
      sighting_date = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'city' field */
    public java.lang.CharSequence getCity() {
      return city;
    }
    
    /** Sets the value of the 'city' field */
    public UFO_Sighting_Record.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.city = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'city' field has been set */
    public boolean hasCity() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'city' field */
    public UFO_Sighting_Record.Builder clearCity() {
      city = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'shape' field */
    public java.lang.CharSequence getShape() {
      return shape;
    }
    
    /** Sets the value of the 'shape' field */
    public UFO_Sighting_Record.Builder setShape(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.shape = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'shape' field has been set */
    public boolean hasShape() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'shape' field */
    public UFO_Sighting_Record.Builder clearShape() {
      shape = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'duration' field */
    public java.lang.Double getDuration() {
      return duration;
    }
    
    /** Sets the value of the 'duration' field */
    public UFO_Sighting_Record.Builder setDuration(double value) {
      validate(fields()[3], value);
      this.duration = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'duration' field has been set */
    public boolean hasDuration() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'duration' field */
    public UFO_Sighting_Record.Builder clearDuration() {
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public UFO_Sighting_Record build() {
      try {
        UFO_Sighting_Record record = new UFO_Sighting_Record();
        record.sighting_date = fieldSetFlags()[0] ? this.sighting_date : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.city = fieldSetFlags()[1] ? this.city : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.shape = fieldSetFlags()[2] ? this.shape : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.duration = fieldSetFlags()[3] ? this.duration : (java.lang.Double) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
