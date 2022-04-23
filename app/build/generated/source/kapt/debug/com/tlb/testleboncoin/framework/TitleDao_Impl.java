package com.tlb.testleboncoin.framework;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.tlb.testleboncoin.framework.db.TitleDao;
import com.tlb.testleboncoin.framework.db.TitleEntity;

import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TitleDao_Impl implements TitleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TitleEntity> __insertionAdapterOfTitleEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearTitles;

  public TitleDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTitleEntity = new EntityInsertionAdapter<TitleEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `TITLES` (`id`,`title`,`albumId`,`url`,`thumbnailUrl`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TitleEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        stmt.bindLong(3, value.getAlbumId());
        if (value.getUrl() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUrl());
        }
        if (value.getThumbnailUrl() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getThumbnailUrl());
        }
      }
    };
    this.__preparedStmtOfClearTitles = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TITLES";
        return _query;
      }
    };
  }

  @Override
  public Object insertTitles(final List<TitleEntity> titles,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTitleEntity.insert(titles);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearTitles(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearTitles.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearTitles.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getTitles(final Continuation<? super List<TitleEntity>> continuation) {
    final String _sql = "SELECT * FROM TITLES";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TitleEntity>>() {
      @Override
      public List<TitleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAlbumId = CursorUtil.getColumnIndexOrThrow(_cursor, "albumId");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfThumbnailUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnailUrl");
          final List<TitleEntity> _result = new ArrayList<TitleEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TitleEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final int _tmpAlbumId;
            _tmpAlbumId = _cursor.getInt(_cursorIndexOfAlbumId);
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpThumbnailUrl;
            if (_cursor.isNull(_cursorIndexOfThumbnailUrl)) {
              _tmpThumbnailUrl = null;
            } else {
              _tmpThumbnailUrl = _cursor.getString(_cursorIndexOfThumbnailUrl);
            }
            _item = new TitleEntity(_tmpId,_tmpTitle,_tmpAlbumId,_tmpUrl,_tmpThumbnailUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
