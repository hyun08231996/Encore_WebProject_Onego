FROM python:3.10-slim-bookworm

ENV LANG=C.UTF-8 \
    TZ=Asia/Seoul \
    DEBIAN_FRONTEND=noninteractive \
    PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    JAVA_HOME=/opt/java/openjdk

RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        ca-certificates \
        openjdk-17-jre-headless \
        tzdata && \
    mkdir -p /opt/java && \
    ln -s /usr/lib/jvm/java-17-openjdk-* /opt/java/openjdk && \
    ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY ./requirements.txt ./
RUN python -m pip install --no-cache-dir --upgrade pip setuptools wheel && \
    python -m pip install --no-cache-dir -r requirements.txt

COPY ./ ./

CMD ["python", "start_flask.py"]
