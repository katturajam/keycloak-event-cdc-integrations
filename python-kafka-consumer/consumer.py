from kafka import KafkaConsumer
import json
import time

consumer = KafkaConsumer(
    'kcl.public.user_entity', 'kcl.public.org', 'kcl.public.realm',
    bootstrap_servers=['localhost:9092'],
    group_id='2',  # Ensure this matches the Kafka group you're using
    auto_offset_reset='earliest',
    enable_auto_commit=False,
    value_deserializer=lambda x: json.loads(x.decode('utf-8'))
)


print("Polling for events...")

try:
    while True:
        messages = consumer.poll(timeout_ms=5000)
        if messages:
            for tp, msgs in messages.items():
                for message in msgs:
                    print(f"Received: {json.dumps(message.value, indent=2)}")
        else:
            print("No messages received. Waiting...")
            time.sleep(1)
except KeyboardInterrupt:
    print("Stopped by user.")
finally:
    consumer.close()
