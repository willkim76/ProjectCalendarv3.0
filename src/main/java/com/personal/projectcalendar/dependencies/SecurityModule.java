package com.personal.projectcalendar.dependencies;

import com.personal.projectcalendar.security.encryptable.AES_CBC_Encryption;
import com.personal.projectcalendar.security.encryptable.Encryptable;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.security.hashable.PBKDF2_Hash;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class SecurityModule {

    @Provides
    @Singleton
    Hashable provideHashable() {
        return new PBKDF2_Hash();
    }

    @Provides
    @Singleton
    Encryptable provideEncryptable() {
        return new AES_CBC_Encryption();
    }
}
