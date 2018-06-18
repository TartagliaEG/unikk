package com.tartaglia.unikk.domain.user;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
abstract class PrincipalDao {

  @Insert
  abstract void save(Principal principal);

  @Query("SELECT * FROM PRINCIPAL WHERE mId = :id")
  abstract Principal findOne(String id);

}
