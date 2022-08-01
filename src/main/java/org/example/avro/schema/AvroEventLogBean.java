/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.example.avro.schema;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class AvroEventLogBean extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3618172655150683393L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroEventLogBean\",\"namespace\":\"org.example.avro.schema\",\"fields\":[{\"name\":\"guid\",\"type\":\"long\"},{\"name\":\"sessionId\",\"type\":\"string\"},{\"name\":\"eventId\",\"type\":\"string\"},{\"name\":\"timeStamp\",\"type\":\"long\"},{\"name\":\"eventInfo\",\"type\":{\"type\":\"map\",\"values\":\"string\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<AvroEventLogBean> ENCODER =
      new BinaryMessageEncoder<AvroEventLogBean>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroEventLogBean> DECODER =
      new BinaryMessageDecoder<AvroEventLogBean>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AvroEventLogBean> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AvroEventLogBean> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AvroEventLogBean> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<AvroEventLogBean>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AvroEventLogBean to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AvroEventLogBean from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AvroEventLogBean instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AvroEventLogBean fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private long guid;
   private java.lang.CharSequence sessionId;
   private java.lang.CharSequence eventId;
   private long timeStamp;
   private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> eventInfo;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroEventLogBean() {}

  /**
   * All-args constructor.
   * @param guid The new value for guid
   * @param sessionId The new value for sessionId
   * @param eventId The new value for eventId
   * @param timeStamp The new value for timeStamp
   * @param eventInfo The new value for eventInfo
   */
  public AvroEventLogBean(java.lang.Long guid, java.lang.CharSequence sessionId, java.lang.CharSequence eventId, java.lang.Long timeStamp, java.util.Map<java.lang.CharSequence,java.lang.CharSequence> eventInfo) {
    this.guid = guid;
    this.sessionId = sessionId;
    this.eventId = eventId;
    this.timeStamp = timeStamp;
    this.eventInfo = eventInfo;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return guid;
    case 1: return sessionId;
    case 2: return eventId;
    case 3: return timeStamp;
    case 4: return eventInfo;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: guid = (java.lang.Long)value$; break;
    case 1: sessionId = (java.lang.CharSequence)value$; break;
    case 2: eventId = (java.lang.CharSequence)value$; break;
    case 3: timeStamp = (java.lang.Long)value$; break;
    case 4: eventInfo = (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'guid' field.
   * @return The value of the 'guid' field.
   */
  public long getGuid() {
    return guid;
  }


  /**
   * Sets the value of the 'guid' field.
   * @param value the value to set.
   */
  public void setGuid(long value) {
    this.guid = value;
  }

  /**
   * Gets the value of the 'sessionId' field.
   * @return The value of the 'sessionId' field.
   */
  public java.lang.CharSequence getSessionId() {
    return sessionId;
  }


  /**
   * Sets the value of the 'sessionId' field.
   * @param value the value to set.
   */
  public void setSessionId(java.lang.CharSequence value) {
    this.sessionId = value;
  }

  /**
   * Gets the value of the 'eventId' field.
   * @return The value of the 'eventId' field.
   */
  public java.lang.CharSequence getEventId() {
    return eventId;
  }


  /**
   * Sets the value of the 'eventId' field.
   * @param value the value to set.
   */
  public void setEventId(java.lang.CharSequence value) {
    this.eventId = value;
  }

  /**
   * Gets the value of the 'timeStamp' field.
   * @return The value of the 'timeStamp' field.
   */
  public long getTimeStamp() {
    return timeStamp;
  }


  /**
   * Sets the value of the 'timeStamp' field.
   * @param value the value to set.
   */
  public void setTimeStamp(long value) {
    this.timeStamp = value;
  }

  /**
   * Gets the value of the 'eventInfo' field.
   * @return The value of the 'eventInfo' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getEventInfo() {
    return eventInfo;
  }


  /**
   * Sets the value of the 'eventInfo' field.
   * @param value the value to set.
   */
  public void setEventInfo(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
    this.eventInfo = value;
  }

  /**
   * Creates a new AvroEventLogBean RecordBuilder.
   * @return A new AvroEventLogBean RecordBuilder
   */
  public static org.example.avro.schema.AvroEventLogBean.Builder newBuilder() {
    return new org.example.avro.schema.AvroEventLogBean.Builder();
  }

  /**
   * Creates a new AvroEventLogBean RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroEventLogBean RecordBuilder
   */
  public static org.example.avro.schema.AvroEventLogBean.Builder newBuilder(org.example.avro.schema.AvroEventLogBean.Builder other) {
    if (other == null) {
      return new org.example.avro.schema.AvroEventLogBean.Builder();
    } else {
      return new org.example.avro.schema.AvroEventLogBean.Builder(other);
    }
  }

  /**
   * Creates a new AvroEventLogBean RecordBuilder by copying an existing AvroEventLogBean instance.
   * @param other The existing instance to copy.
   * @return A new AvroEventLogBean RecordBuilder
   */
  public static org.example.avro.schema.AvroEventLogBean.Builder newBuilder(org.example.avro.schema.AvroEventLogBean other) {
    if (other == null) {
      return new org.example.avro.schema.AvroEventLogBean.Builder();
    } else {
      return new org.example.avro.schema.AvroEventLogBean.Builder(other);
    }
  }

  /**
   * RecordBuilder for AvroEventLogBean instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroEventLogBean>
    implements org.apache.avro.data.RecordBuilder<AvroEventLogBean> {

    private long guid;
    private java.lang.CharSequence sessionId;
    private java.lang.CharSequence eventId;
    private long timeStamp;
    private java.util.Map<java.lang.CharSequence,java.lang.CharSequence> eventInfo;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.example.avro.schema.AvroEventLogBean.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.guid)) {
        this.guid = data().deepCopy(fields()[0].schema(), other.guid);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.sessionId)) {
        this.sessionId = data().deepCopy(fields()[1].schema(), other.sessionId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.eventId)) {
        this.eventId = data().deepCopy(fields()[2].schema(), other.eventId);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[3].schema(), other.timeStamp);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.eventInfo)) {
        this.eventInfo = data().deepCopy(fields()[4].schema(), other.eventInfo);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing AvroEventLogBean instance
     * @param other The existing instance to copy.
     */
    private Builder(org.example.avro.schema.AvroEventLogBean other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.guid)) {
        this.guid = data().deepCopy(fields()[0].schema(), other.guid);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.sessionId)) {
        this.sessionId = data().deepCopy(fields()[1].schema(), other.sessionId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.eventId)) {
        this.eventId = data().deepCopy(fields()[2].schema(), other.eventId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[3].schema(), other.timeStamp);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.eventInfo)) {
        this.eventInfo = data().deepCopy(fields()[4].schema(), other.eventInfo);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'guid' field.
      * @return The value.
      */
    public long getGuid() {
      return guid;
    }


    /**
      * Sets the value of the 'guid' field.
      * @param value The value of 'guid'.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder setGuid(long value) {
      validate(fields()[0], value);
      this.guid = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'guid' field has been set.
      * @return True if the 'guid' field has been set, false otherwise.
      */
    public boolean hasGuid() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'guid' field.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder clearGuid() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sessionId' field.
      * @return The value.
      */
    public java.lang.CharSequence getSessionId() {
      return sessionId;
    }


    /**
      * Sets the value of the 'sessionId' field.
      * @param value The value of 'sessionId'.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder setSessionId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.sessionId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sessionId' field has been set.
      * @return True if the 'sessionId' field has been set, false otherwise.
      */
    public boolean hasSessionId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sessionId' field.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder clearSessionId() {
      sessionId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventId' field.
      * @return The value.
      */
    public java.lang.CharSequence getEventId() {
      return eventId;
    }


    /**
      * Sets the value of the 'eventId' field.
      * @param value The value of 'eventId'.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder setEventId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.eventId = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'eventId' field has been set.
      * @return True if the 'eventId' field has been set, false otherwise.
      */
    public boolean hasEventId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'eventId' field.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder clearEventId() {
      eventId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'timeStamp' field.
      * @return The value.
      */
    public long getTimeStamp() {
      return timeStamp;
    }


    /**
      * Sets the value of the 'timeStamp' field.
      * @param value The value of 'timeStamp'.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder setTimeStamp(long value) {
      validate(fields()[3], value);
      this.timeStamp = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'timeStamp' field has been set.
      * @return True if the 'timeStamp' field has been set, false otherwise.
      */
    public boolean hasTimeStamp() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'timeStamp' field.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder clearTimeStamp() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'eventInfo' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.CharSequence> getEventInfo() {
      return eventInfo;
    }


    /**
      * Sets the value of the 'eventInfo' field.
      * @param value The value of 'eventInfo'.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder setEventInfo(java.util.Map<java.lang.CharSequence,java.lang.CharSequence> value) {
      validate(fields()[4], value);
      this.eventInfo = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'eventInfo' field has been set.
      * @return True if the 'eventInfo' field has been set, false otherwise.
      */
    public boolean hasEventInfo() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'eventInfo' field.
      * @return This builder.
      */
    public org.example.avro.schema.AvroEventLogBean.Builder clearEventInfo() {
      eventInfo = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroEventLogBean build() {
      try {
        AvroEventLogBean record = new AvroEventLogBean();
        record.guid = fieldSetFlags()[0] ? this.guid : (java.lang.Long) defaultValue(fields()[0]);
        record.sessionId = fieldSetFlags()[1] ? this.sessionId : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.eventId = fieldSetFlags()[2] ? this.eventId : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.timeStamp = fieldSetFlags()[3] ? this.timeStamp : (java.lang.Long) defaultValue(fields()[3]);
        record.eventInfo = fieldSetFlags()[4] ? this.eventInfo : (java.util.Map<java.lang.CharSequence,java.lang.CharSequence>) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroEventLogBean>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroEventLogBean>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroEventLogBean>
    READER$ = (org.apache.avro.io.DatumReader<AvroEventLogBean>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.guid);

    out.writeString(this.sessionId);

    out.writeString(this.eventId);

    out.writeLong(this.timeStamp);

    long size0 = this.eventInfo.size();
    out.writeMapStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (java.util.Map.Entry<java.lang.CharSequence, java.lang.CharSequence> e0: this.eventInfo.entrySet()) {
      actualSize0++;
      out.startItem();
      out.writeString(e0.getKey());
      java.lang.CharSequence v0 = e0.getValue();
      out.writeString(v0);
    }
    out.writeMapEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Map-size written was " + size0 + ", but element count was " + actualSize0 + ".");

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.guid = in.readLong();

      this.sessionId = in.readString(this.sessionId instanceof Utf8 ? (Utf8)this.sessionId : null);

      this.eventId = in.readString(this.eventId instanceof Utf8 ? (Utf8)this.eventId : null);

      this.timeStamp = in.readLong();

      long size0 = in.readMapStart();
      java.util.Map<java.lang.CharSequence,java.lang.CharSequence> m0 = this.eventInfo; // Need fresh name due to limitation of macro system
      if (m0 == null) {
        m0 = new java.util.HashMap<java.lang.CharSequence,java.lang.CharSequence>((int)size0);
        this.eventInfo = m0;
      } else m0.clear();
      for ( ; 0 < size0; size0 = in.mapNext()) {
        for ( ; size0 != 0; size0--) {
          java.lang.CharSequence k0 = null;
          k0 = in.readString(k0 instanceof Utf8 ? (Utf8)k0 : null);
          java.lang.CharSequence v0 = null;
          v0 = in.readString(v0 instanceof Utf8 ? (Utf8)v0 : null);
          m0.put(k0, v0);
        }
      }

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.guid = in.readLong();
          break;

        case 1:
          this.sessionId = in.readString(this.sessionId instanceof Utf8 ? (Utf8)this.sessionId : null);
          break;

        case 2:
          this.eventId = in.readString(this.eventId instanceof Utf8 ? (Utf8)this.eventId : null);
          break;

        case 3:
          this.timeStamp = in.readLong();
          break;

        case 4:
          long size0 = in.readMapStart();
          java.util.Map<java.lang.CharSequence,java.lang.CharSequence> m0 = this.eventInfo; // Need fresh name due to limitation of macro system
          if (m0 == null) {
            m0 = new java.util.HashMap<java.lang.CharSequence,java.lang.CharSequence>((int)size0);
            this.eventInfo = m0;
          } else m0.clear();
          for ( ; 0 < size0; size0 = in.mapNext()) {
            for ( ; size0 != 0; size0--) {
              java.lang.CharSequence k0 = null;
              k0 = in.readString(k0 instanceof Utf8 ? (Utf8)k0 : null);
              java.lang.CharSequence v0 = null;
              v0 = in.readString(v0 instanceof Utf8 ? (Utf8)v0 : null);
              m0.put(k0, v0);
            }
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










