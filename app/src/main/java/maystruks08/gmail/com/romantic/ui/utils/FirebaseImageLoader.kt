package maystruks08.gmail.com.romantic.ui.utils

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable

import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StreamDownloadTask

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.security.MessageDigest


class FirebaseImageLoader : ModelLoader<StorageReference, InputStream> {

    class Factory : ModelLoaderFactory<StorageReference, InputStream> {

        @NonNull
        override fun build(@NonNull factory: MultiModelLoaderFactory): ModelLoader<StorageReference, InputStream> {
            return FirebaseImageLoader()
        }

        override fun teardown() {
        }
    }

    @Nullable
    override fun buildLoadData(
        @NonNull reference: StorageReference,
        height: Int,
        width: Int,
        @NonNull options: Options
    ): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(
            FirebaseStorageKey(reference),
            FirebaseStorageFetcher(reference)
        )
    }

    override fun handles(@NonNull reference: StorageReference): Boolean {
        return true
    }

    private class FirebaseStorageKey(private val mRef: StorageReference) : Key {

        override fun updateDiskCacheKey(@NonNull digest: MessageDigest) {
            digest.update(mRef.path.toByteArray(Charset.defaultCharset()))
        }

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false

            val key = o as FirebaseStorageKey?

            return mRef == key!!.mRef
        }

        override fun hashCode(): Int {
            return mRef.hashCode()
        }
    }

    private class FirebaseStorageFetcher(private val mRef: StorageReference) : DataFetcher<InputStream> {
        private var mStreamTask: StreamDownloadTask? = null
        private var mInputStream: InputStream? = null

        override fun loadData(
            @NonNull priority: Priority,
            @NonNull callback: DataFetcher.DataCallback<in InputStream>
        ) {
            mStreamTask = mRef.stream
            mStreamTask!!
                .addOnSuccessListener { snapshot ->
                    mInputStream = snapshot.stream
                    callback.onDataReady(mInputStream)
                }
                .addOnFailureListener { e -> callback.onLoadFailed(e) }
        }

        override fun cleanup() {
            if (mInputStream != null) {
                try {
                    mInputStream!!.close()
                    mInputStream = null
                } catch (e: IOException) {
                    Log.w(TAG, "Could not close stream", e)
                }

            }
        }

        override fun cancel() {
            if (mStreamTask != null && mStreamTask!!.isInProgress) {
                mStreamTask!!.cancel()
            }
        }

        @NonNull
        override fun getDataClass(): Class<InputStream> {
            return InputStream::class.java
        }

        @NonNull
        override fun getDataSource(): DataSource {
            return DataSource.REMOTE
        }
    }

    companion object {

        private val TAG = "FirebaseImageLoader"
    }
}