# https://hub.docker.com/r/theeluwin/ubuntu-konlpy/dockerfile
FROM ubuntu:22.04

# apt init
ENV LANG=C.UTF-8
ENV TZ=Asia/Seoul
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        tzdata \
        g++ \
        git \
        curl \
        openjdk-17-jdk \
        python3 \
        python3-venv \
        python3-pip \
        python3-setuptools \
        python3-dev \
        python3-numpy \
        python3-pandas \
        python3-matplotlib \
        python3-networkx \
        python3-scipy \
        python3-lxml \
        libxml2-dev \
        libxslt1-dev \
        zlib1g-dev \
        libblas-dev \
        liblapack-dev \
        gfortran && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# timezone
RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN python3 -m venv --system-site-packages /opt/venv
ENV PATH="/opt/venv/bin:$PATH"
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

WORKDIR /app

# install python packages
RUN /opt/venv/bin/python3 -m pip install --no-cache-dir --upgrade pip setuptools wheel
RUN /opt/venv/bin/python3 -m pip install --no-cache-dir konlpy
COPY ./requirements.txt ./
RUN grep -vE '^(lxml|matplotlib|networkx|pandas|scipy|numpy)==' requirements.txt > /tmp/reqs && /opt/venv/bin/python3 -m pip install --no-cache-dir --no-build-isolation -r /tmp/reqs

COPY ./ ./

CMD ["python3", "start_flask.py"]
