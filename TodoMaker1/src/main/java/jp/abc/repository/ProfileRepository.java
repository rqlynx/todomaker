package jp.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.abc.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByName(String name);
}
