git clone https://github.com/zetiko8/vaadin-tutorial.git

cd vaadin-tutorial

docker build --tag tutorial .

docker run -d -p 8080:8080 --name tutorial tutorial