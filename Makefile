run: compile
	java Frontend
compile: Frontend.class Backend.class Movie.class MovieDataReader.class TestBackend.class TestFrontend.class TestMovieAndMovieDataReader.class BackendInterface.class MovieDataReaderInterface.class MovieInterface.class

Frontend.class: Frontend.java
	javac Frontend.java

Backend.class: Backend.java
	javac Backend.java

Movie.class: Movie.java
	javac Movie.java

MovieDataReader.class: MovieDataReader.java
	javac MovieDataReader.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

MovieDataReaderInterface.class: MovieDataReaderInterface.java
	javac MovieDataReaderInterface.java

MovieInterface.class: MovieInterface.java
	javac MovieInterface.java

TestBackend.class: TestBackend.java
	javac TestBackend.java

TestFrontend.class: TestFrontend.java
	javac TestFrontend.java

TestMovieAndMovieDataReader.class: TestMovieAndMovieDataReader.java
	javac TestMovieAndMovieDataReader.java


test: testData testBackend testFrontend

testData: TestMovieAndMovieDataReader.class
	java TestMovieAndMovieDataReader

testFrontend: TestFrontend.class
	java TestFrontend

testBackend: BackendInterface.class TestBackend.class
	java TestBackend

clean:
	$(RM) *.class