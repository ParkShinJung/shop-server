FROM python:3.10

RUN mkdir /opt/code
RUN pip install --upgrade pip

WORKDIR /opt/code

COPY requirements.txt .
RUN pip install -r requirements.txt

EXPOSE 8080