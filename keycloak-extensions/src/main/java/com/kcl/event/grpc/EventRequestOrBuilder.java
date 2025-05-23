// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: kcl_event.proto

package com.kcl.event.grpc;

public interface EventRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.kcl.event.grpc.EventRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Type of event, e.g., "USER_EVENT" or "ADMIN_EVENT"
   * </pre>
   *
   * <code>string event_type = 1;</code>
   * @return The eventType.
   */
  java.lang.String getEventType();
  /**
   * <pre>
   * Type of event, e.g., "USER_EVENT" or "ADMIN_EVENT"
   * </pre>
   *
   * <code>string event_type = 1;</code>
   * @return The bytes for eventType.
   */
  com.google.protobuf.ByteString
      getEventTypeBytes();

  /**
   * <pre>
   * Realm in which the event occurred
   * </pre>
   *
   * <code>string realm_id = 2;</code>
   * @return The realmId.
   */
  java.lang.String getRealmId();
  /**
   * <pre>
   * Realm in which the event occurred
   * </pre>
   *
   * <code>string realm_id = 2;</code>
   * @return The bytes for realmId.
   */
  com.google.protobuf.ByteString
      getRealmIdBytes();

  /**
   * <pre>
   * Client that triggered the event
   * </pre>
   *
   * <code>string client_id = 3;</code>
   * @return The clientId.
   */
  java.lang.String getClientId();
  /**
   * <pre>
   * Client that triggered the event
   * </pre>
   *
   * <code>string client_id = 3;</code>
   * @return The bytes for clientId.
   */
  com.google.protobuf.ByteString
      getClientIdBytes();

  /**
   * <pre>
   * User ID involved in the event (optional for admin events)
   * </pre>
   *
   * <code>string user_id = 4;</code>
   * @return The userId.
   */
  java.lang.String getUserId();
  /**
   * <pre>
   * User ID involved in the event (optional for admin events)
   * </pre>
   *
   * <code>string user_id = 4;</code>
   * @return The bytes for userId.
   */
  com.google.protobuf.ByteString
      getUserIdBytes();

  /**
   * <pre>
   * IP address from where the event originated
   * </pre>
   *
   * <code>string ip_address = 5;</code>
   * @return The ipAddress.
   */
  java.lang.String getIpAddress();
  /**
   * <pre>
   * IP address from where the event originated
   * </pre>
   *
   * <code>string ip_address = 5;</code>
   * @return The bytes for ipAddress.
   */
  com.google.protobuf.ByteString
      getIpAddressBytes();

  /**
   * <pre>
   * JSON payload representing the full event data
   * </pre>
   *
   * <code>string payload_json = 6;</code>
   * @return The payloadJson.
   */
  java.lang.String getPayloadJson();
  /**
   * <pre>
   * JSON payload representing the full event data
   * </pre>
   *
   * <code>string payload_json = 6;</code>
   * @return The bytes for payloadJson.
   */
  com.google.protobuf.ByteString
      getPayloadJsonBytes();
}
