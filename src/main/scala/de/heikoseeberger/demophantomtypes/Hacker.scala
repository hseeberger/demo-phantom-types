/*
 * Copyright 2016 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.demophantomtypes

import scala.annotation.implicitNotFound

object Hacker {

  @implicitNotFound("This hacker is in dire need of coffee!")
  type IsCaffeinated[S] = S =:= State.Caffeinated

  @implicitNotFound("This hacker already had coffee and needs to do some hacking first!")
  type IsDecaffeinated[S] = S =:= State.Decaffeinated

  sealed trait State
  object State {
    sealed trait Caffeinated extends State
    sealed trait Decaffeinated extends State
  }

  def caffeinated: Hacker[State.Caffeinated] = new Hacker
  def decaffeinated: Hacker[State.Decaffeinated] = new Hacker
}

class Hacker[S <: Hacker.State] private {
  import Hacker._

  def hackOn(implicit ev: IsCaffeinated[S]): Hacker[State.Decaffeinated] = {
    println("Hacking, hacking, hacking!")
    new Hacker
  }

  def drinkCoffee(implicit ev: IsDecaffeinated[S]): Hacker[State.Caffeinated] = {
    println("Slurp ...")
    new Hacker
  }
}
