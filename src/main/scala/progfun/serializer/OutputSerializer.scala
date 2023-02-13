package progfun.serializer

trait OutputSerializer[A, B, C] {
  def serialize(herbe: A, mowers: B): C
}