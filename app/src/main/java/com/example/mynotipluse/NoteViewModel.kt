package com.example.mynotipluse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynotipluse.room.Note
import com.example.mynotipluse.room.NoteRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NoteViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {

    var allNoteResult: MutableLiveData<List<Note>> = MutableLiveData()
    var allNoteError: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<Note>>
    var compositeDisposable = CompositeDisposable()


    fun allNoteError(): LiveData<String> {
        return allNoteError
    }

    fun getAllNotes(): LiveData<List<Note>> {

        compositeDisposable.add(
            noteRepository.getAllNotes()
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    allNoteResult.postValue(it)

                }, {
                    allNoteError.postValue(it.message)


                })

        )
        /*disposableObserver = object : DisposableObserver<List<Note>>() {
            override fun onNext(t: List<Note>) {
                allNoteResult.postValue(t)
            }

            override fun onError(e: Throwable) {

                allNoteError.postValue(e.message)
            }

            override fun onComplete() {
            }


        }

        noteRepository.getAllNotes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
           // .debounce(400, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
*/
        return allNoteResult
    }

    fun insertNote(note: Note) {
        Single.fromCallable {
            noteRepository.insertNote(note = note)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()


    }

/*
    fun disposeElements() {

        if (null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }*/
}