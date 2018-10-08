FROM maven:3-jdk-11
VOLUME /tmp

ENV SPARK_VERSION spark-2.3.2-bin-hadoop2.7
ENV SPARK_HOME /usr/local/spark

RUN curl https://archive.apache.org/dist/spark/spark-2.3.2/$SPARK_VERSION.tgz -o $SPARK_VERSION.tgz; \
                 tar xzf $SPARK_VERSION.tgz -C /usr/local/;
RUN cd /usr/local && ln -s $SPARK_VERSION spark

ENV PROJECT_HOME /usr/local/project

ADD src/main/java $PROJECT_HOME/src/main/java
ADD src/main/resources $PROJECT_HOME/src/main/resources
ADD csv $PROJECT_HOME/csv

WORKDIR $PROJECT_HOME

RUN mvn package

WORKDIR $SPARK_HOME

CMD ["bin/spark-submit", "--class", "Application", "--master", "local[2]", "/usr/local/project/target/spark-custom-datasource-0.1.0.jar", "/usr/local/project/csv" ]
