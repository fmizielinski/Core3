package example.core3.domain.mapper

import example.core3.data.entity.User
import example.core3.domain.dto.UserDto

class UserMapper {

    fun map(user: User) =
        UserDto(user.name, user.email)
}