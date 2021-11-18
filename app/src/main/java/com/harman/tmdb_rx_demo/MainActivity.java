package com.harman.tmdb_rx_demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mMoviesRecycler;
    private RxTextView mRxTet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesRecycler = findViewById(R.id.movies_recycler_view);

        mMoviesRecycler.setLayoutManager(new LinearLayoutManager(this));

        Controller controller = new Controller();

        Single<MoviesResponse> moviesSingle = controller.prepareMoviesCallRX();
        moviesSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MoviesResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MoviesResponse moviesResponse) {
                        List<Movie> moviesList = moviesResponse.results;
                        mMoviesRecycler.setAdapter(new MoviesAdapter(moviesList));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }
                });
    }
}