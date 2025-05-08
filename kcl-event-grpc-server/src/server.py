import asyncio
import grpc
import json
import kcl_event_pb2 as event_pb2
import kcl_event_pb2_grpc as event_pb2_grpc

class EventService(event_pb2_grpc.EventServiceServicer):
    async def StreamEvent(self, request_iterator, context):
        try:
            async for event in request_iterator:
                print(f"Received event_type={event.event_type} user_id={event.user_id}")

                # Deserialize JSON payload safely
                try:
                    payload = json.loads(event.payload_json)
                    if payload.get("representation"):
                        payload['representation'] = json.loads(payload['representation'])
                    print("Payload:", json.dumps(payload, indent=2))
                except json.JSONDecodeError:
                    print("Invalid JSON payload received.")
                    continue

                # (Optional) Send to Kafka, RabbitMQ, etc.
                # await self.kafka_producer.send("keycloak-events", payload)

            return event_pb2.EventResponse(status="OK")

        except Exception as e:
            print(f"Error processing stream: {e}")
            return event_pb2.EventResponse(status="ERROR")

async def serve():
    server = grpc.aio.server()
    event_pb2_grpc.add_EventServiceServicer_to_server(EventService(), server)

    listen_addr = '[::]:50051'
    server.add_insecure_port(listen_addr)

    await server.start()
    print(f"gRPC server started on {listen_addr}")
    await server.wait_for_termination()

if __name__ == "__main__":
    asyncio.run(serve())
