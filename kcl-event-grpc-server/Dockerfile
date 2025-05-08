FROM python:3.13-slim

# Set working directory
WORKDIR /app

# Copy the requirements.txt and install dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy the source code
COPY ./src .

# Set the default command to run your Python application
ENTRYPOINT ["python", "-u", "server.py"]
