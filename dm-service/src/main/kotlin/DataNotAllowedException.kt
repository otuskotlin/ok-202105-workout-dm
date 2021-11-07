import ModelForRequest.cruds.BaseMessage

class DataNotAllowedException(msg: String, request: BaseMessage) : Throwable("$msg: $request")