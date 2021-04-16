package sandbox

import cats.implicits._
import cats.data.Kleisli


// final case class Kleisli[F[_], -A, B](run: A => F[B])

case class User(username: String, avatarUrl: String, profileId: Int)
case class Avatar(url: String, monochrome: Boolean)
case class Profile(id: Int, age: Int)

class ComposingStuff extends App {

  val users: List[User] = Nil
  val avatars: List[Avatar] = Nil
  val profiles: List[Profile] = Nil

  type Result[A] = Either[String, A]

  val fetchUser: Kleisli[Result, String, User] =
    Kleisli { username: String =>
        users
          .find(_.username == username)
          .toRight("could not find user")
    }

  val fetchAvatar: Kleisli[Result, User, Avatar] =
    Kleisli{(user: User) =>
      avatars
        .find(_.url == user.avatarUrl)
        .toRight("could not find avatar")
    }

  val fetchProfile: Kleisli[Result, User, Profile] =
    Kleisli{(user: User) =>
      profiles
        .find(_.id == user.profileId)
        .toRight("could not find profile")
    }

  // parallel if using par..
  // tupled unpacks things eg two options tupled should be the some values
  val fetchAvatarAndProfile: Kleisli[Result, User, (Avatar, Profile)] =
    (fetchAvatar, fetchProfile).parTupled

  // sequential
  val fetchAvatarAndProfileByUsername =
    fetchUser.andThen(fetchAvatarAndProfile)

  fetchAvatarAndProfileByUsername("dave")
}
