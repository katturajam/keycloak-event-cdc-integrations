# 1. Install Python packages
```bash
pip install -r requirements.txt
```
# 2. Generate Python Protobuf + gRPC
```bash
python -m grpc_tools.protoc \
  -Iprotos \
  --python_out=kcl-event-grpc-server/src \
  --grpc_python_out=kcl-event-grpc-server/src \
  protos/kcl_event.proto
```